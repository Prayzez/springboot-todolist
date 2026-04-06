package com.example.springbasicfinal.controller;

import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springbasicfinal.entity.Todo;
import com.example.springbasicfinal.form.TodoForm;
import com.example.springbasicfinal.repository.TodoRepository;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // List
    @GetMapping
    public String index(Model model) {
        model.addAttribute("todos", todoRepository.findAllByOrderByUpdatedAtDesc());
        return "todo/index";
    }

    // Detail
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        Optional<Todo> todoOpt = todoRepository.findById(id);
        if (todoOpt.isEmpty()) {
            ra.addFlashAttribute("infoMessage", "指定されたTodoが見つかりませんでした。");
            return "redirect:/todos";
        }
        model.addAttribute("todo", todoOpt.get());
        return "todo/detail";
    }

    // Create (show)
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("todoForm", new TodoForm());
        return "todo/create";
    }

    // Create (submit)
    @PostMapping("/create")
    public String createPost(@Valid @ModelAttribute("todoForm") TodoForm todoForm,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "入力内容に誤りがあります。");
            return "todo/create";
        }

        Todo todo = new Todo();
        todo.setTitle(todoForm.getTitle());
        todo.setContent(todoForm.getContent());
        todoRepository.save(todo);

        return "redirect:/todos";
    }

    // Update (show)
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        Optional<Todo> todoOpt = todoRepository.findById(id);
        if (todoOpt.isEmpty()) {
            ra.addFlashAttribute("infoMessage", "指定されたTodoが見つかりませんでした。");
            return "redirect:/todos";
        }

        Todo todo = todoOpt.get();
        TodoForm todoForm = new TodoForm();
        todoForm.setTitle(todo.getTitle());
        todoForm.setContent(todo.getContent());

        model.addAttribute("todoForm", todoForm);
        model.addAttribute("todoId", id);
        return "todo/update";
    }

    // Update (submit)
    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable("id") Long id,
                             @Valid @ModelAttribute("todoForm") TodoForm todoForm,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes ra) {
        Optional<Todo> todoOpt = todoRepository.findById(id);
        if (todoOpt.isEmpty()) {
            ra.addFlashAttribute("infoMessage", "指定されたTodoが見つかりませんでした。");
            return "redirect:/todos";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "入力内容に誤りがあります。");
            model.addAttribute("todoId", id);
            return "todo/update";
        }

        Todo todo = todoOpt.get();
        todo.setTitle(todoForm.getTitle());
        todo.setContent(todoForm.getContent());
        todoRepository.save(todo);

        return "redirect:/todos";
    }

    // Delete (GET link as required)
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
        } else {
            ra.addFlashAttribute("infoMessage", "指定されたTodoが見つかりませんでした。");
        }
        return "redirect:/todos";
    }
}
