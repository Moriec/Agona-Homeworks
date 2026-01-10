package com.vinogradov;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class CheckTodoPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        CheckTodoTask task = project.getTasks().create("checkTodo", CheckTodoTask.class);
        task.setGroup("verification");

        project.getTasks().matching(t -> t.getName().equals("check"))
                .all(t -> t.dependsOn(task));
    }
}
