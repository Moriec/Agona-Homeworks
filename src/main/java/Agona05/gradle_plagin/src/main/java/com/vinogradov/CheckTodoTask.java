package com.vinogradov;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import java.util.List;

public class CheckTodoTask extends DefaultTask {

    private String todoPattern = "//\\s*(TODO|FIXME).*";
    private String taskPattern;
    private String developBranch = "develop";

    @Input
    public String getTodoPattern() {
        return todoPattern;
    }

    public void setTodoPattern(String todoPattern) {
        this.todoPattern = todoPattern;
    }

    @Input
    public String getTaskPattern() {
        return taskPattern;
    }

    public void setTaskPattern(String taskPattern) {
        this.taskPattern = taskPattern;
    }

    @Input
    public String getDevelopBranch() {
        return developBranch;
    }

    public void setDevelopBranch(String developBranch) {
        this.developBranch = developBranch;
    }

    @TaskAction
    public void check() {
        if (getTaskPattern() == null || getTaskPattern().isEmpty()) {
            throw new GradleException("taskPattern must specified for CheckTodoTask");
        }

        List<String> errors;
        try {
            TodoChecker checker = new TodoChecker();
            errors = checker.check(
                    getProject().getProjectDir(),
                    getDevelopBranch(),
                    getTodoPattern(),
                    getTaskPattern()
            );
        } catch (Exception e) {
            throw new GradleException("Fatalka Failed", e);
        }

        if (!errors.isEmpty()) {
            getLogger().error("Check TODO Failed: Found %s invelid comments".formatted(errors.size()));
            errors.forEach(getLogger()::info);
            throw new GradleException("Found %s invelid comments".formatted(errors.size()));
        } else {
            getLogger().info("Check Todo: SUCCESS");
        }
    }
}
