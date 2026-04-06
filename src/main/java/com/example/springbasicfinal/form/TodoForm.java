package com.example.springbasicfinal.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TodoForm {

    @NotBlank(message = "タイトルは必須です。")
    @Size(max = 50, message = "タイトルは50文字以内で入力してください。")
    private String title;

    private String content;

    public TodoForm() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
