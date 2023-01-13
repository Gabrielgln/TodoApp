/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import controller.ProjectController;
import controller.TaskController;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;

/**
 *
 * @author gabri
 */
public class main {
    public static void main(String[]args){
        
        
        ProjectController projectController =  new ProjectController();
        
        Project project = new Project();
        project.setName("Projeto novo");
        project.setDescription("description");
        projectController.save(project);
        
        TaskController taskController = new TaskController();
        
        Task task = new Task();
        task.setIdProject(1);
        task.setName("tarefa");
        task.setDescription("tarefa massa");
        task.setNotes("terminar logo");
        task.setDeadline(new Date());
        
        taskController.save(task);
        
        
        //project.setName("novo projeto");
        //project.setId(1);
        //projectController.update(project);
        
        //List<Project> projects = projectController.getAll();
        //System.out.println("Total de projetos: "+projects.size());
        
        //projectController.removeById(5);
        
        //System.out.println("Total de projetos: "+projects.size());
        
    }
}
