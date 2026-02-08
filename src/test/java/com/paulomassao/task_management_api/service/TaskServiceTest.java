package com.paulomassao.task_management_api.service;

import com.paulomassao.task_management_api.entity.Task;
import com.paulomassao.task_management_api.enums.TaskStatus;
import com.paulomassao.task_management_api.exception.ResourceNotFoundException;
import com.paulomassao.task_management_api.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setStatus(TaskStatus.TODO);
    }

    @Test
    @DisplayName("Should create task successfully")
    void createTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task created = taskService.createTask(task);

        assertNotNull(created);
        assertEquals("Test Task", created.getTitle());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("Should return all tasks")
    void getAllTasks() {
        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");
        task2.setStatus(TaskStatus.DONE);

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task, task2));

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find task by ID")
    void getTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task found = taskService.getTaskById(1L);

        assertEquals("Test Task", found.getTitle());
    }

    @Test
    @DisplayName("Should throw exception when task not found")
    void getTaskByIdNotFound() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> taskService.getTaskById(99L));
    }

    @Test
    @DisplayName("Should update task successfully")
    void updateTask() {
        Task updated = new Task();
        updated.setTitle("Updated Title");
        updated.setDescription("Updated Desc");
        updated.setStatus(TaskStatus.DONE);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.updateTask(1L, updated);

        assertNotNull(result);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("Should delete task successfully")
    void deleteTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        doNothing().when(taskRepository).delete(task);

        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    @DisplayName("Should find tasks by status")
    void getTasksByStatus() {
        when(taskRepository.findByStatus(TaskStatus.TODO))
                .thenReturn(Arrays.asList(task));

        List<Task> tasks = taskService.getTasksByStatus(TaskStatus.TODO);

        assertEquals(1, tasks.size());
        assertEquals(TaskStatus.TODO, tasks.get(0).getStatus());
    }
}