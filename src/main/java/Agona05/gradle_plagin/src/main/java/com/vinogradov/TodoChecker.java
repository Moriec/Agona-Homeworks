package com.vinogradov;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.Edit;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.patch.FileHeader;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.util.io.DisabledOutputStream;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TodoChecker {

    public List<String> check(File projectDir, String developBranch, String todoPattern, String taskPattern) throws Exception {
        List<String> errors = new ArrayList<>();

        try (Repository repository = new FileRepositoryBuilder().setGitDir(new File(projectDir, ".git")).readEnvironment().findGitDir().build();
             Git git = new Git(repository)) {

            ObjectId head = repository.resolve("HEAD");
            ObjectId develop = repository.resolve(developBranch);

            if (head == null || develop == null) {
                return errors;
            }

            try (DiffFormatter diffFormatter = new DiffFormatter(DisabledOutputStream.INSTANCE)) {
                diffFormatter.setRepository(repository);
                diffFormatter.setDiffComparator(RawTextComparator.DEFAULT);
                diffFormatter.setDetectRenames(true);

                List<DiffEntry> diffs = diffFormatter.scan(develop, head);
                Pattern todoRegex = Pattern.compile(todoPattern);
                Pattern taskRegex = Pattern.compile(taskPattern);

                for (DiffEntry entry : diffs) {
                    if (entry.getChangeType() == DiffEntry.ChangeType.DELETE) {
                        continue;
                    }

                    ObjectId newId = entry.getNewId().toObjectId();
                    if (newId == null || newId.equals(ObjectId.zeroId())) {
                        continue;
                    }

                    FileHeader header = diffFormatter.toFileHeader(entry);

                    String[] lines = readFileLines(repository, newId);

                    for (Edit edit : header.toEditList()) {
                        if (edit.getType() == Edit.Type.INSERT || edit.getType() == Edit.Type.REPLACE) {
                            for (int i = edit.getBeginB(); i < edit.getEndB(); i++) {
                                if (i < lines.length) {
                                    String actualLine = lines[i];
                                    if (todoRegex.matcher(actualLine).find()) {
                                        if (!taskRegex.matcher(actualLine).matches()) {
                                            errors.add(String.format("TODO without task: %s:%d -> %s",
                                                    entry.getNewPath(), (i + 1), actualLine.trim()));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return errors;
    }

    private String[] readFileLines(Repository repo, ObjectId objId) throws Exception {
        ObjectLoader loader = repo.open(objId);
        String content = new String(loader.getBytes(), StandardCharsets.UTF_8);
        return content.split("\r?\n");
    }
}
