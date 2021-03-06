package com.example.demopost.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts")
public class Post extends AuditModel{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max=100)
    @Column(unique = true)
    private String title;
    @NotNull
    @Size(max=250)
    private String description;
    @NotNull
    @Lob //para soportar tamaño máximo, alfanuméricos, imágenes
    private String content;
}
