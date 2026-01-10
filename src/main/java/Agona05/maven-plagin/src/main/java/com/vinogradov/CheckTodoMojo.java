package com.vinogradov;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.List;

@Mojo(name = "check-todo", defaultPhase = LifecyclePhase.PREPARE_PACKAGE)
public class CheckTodoMojo extends AbstractMojo {

    @Parameter(property = "todoPattern", defaultValue = "//\\s*(TODO|FIXME).*")
    private String todoPattern;

    @Parameter(property = "taskPattern", required = true)
    private String taskPattern;

    @Parameter(property = "developBranch", defaultValue = "develop")
    private String developBranch;

    @Parameter(defaultValue = "${project.basedir}", readonly = true)
    private File projectBasedir;

    public void execute() throws MojoFailureException {
        getLog().info("Check invelid TODO or FIXME");
        try {
            TodoChecker checker = new TodoChecker();
            List<String> errors = checker.check(projectBasedir, developBranch, todoPattern, taskPattern);

            if (!errors.isEmpty()) {
                for (String error : errors) {
                    getLog().error(error);
                }
                throw new MojoFailureException(String.format("Found %s invalid TODO comments.", errors.size()));
            }
        } catch (Exception e) {
            throw new MojoFailureException("Failed to check invelid TODO", e);
        }
    }
}
