package com.example.springbootthymeleafcrud.controller;

import com.example.springbootthymeleafcrud.entity.Student;
import com.example.springbootthymeleafcrud.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping(value = {"/","/index"})
    public String showStudentList(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Student student) {
        return "add-student";
    }

    @PostMapping("/addstudent")
    public String add(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }

        studentRepository.save(student);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);

        return "update-student";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Student Student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Student.setId(id);
            return "update-Student";
        }

        studentRepository.save(Student);

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Student Student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Student Id:" + id));
        studentRepository.delete(Student);
        return "redirect:/index";
    }

    @GetMapping("/searchstudent")
    public String searchStudents(@RequestParam(name = "keyword") String keyword, Model model) {
        model.addAttribute("students",studentRepository.searchStudent(keyword));
        return "index";
    }
}
