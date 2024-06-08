package com.example.kotlin

import com.example.kotlin.entities.Project
import com.example.kotlin.repository.CompanyRepository
import com.example.kotlin.services.CompanyService
import java.util.Scanner

fun main() {
    val repository = CompanyRepository()
    val service = CompanyService(repository)
    val scanner = Scanner(System.`in`)

    while (true) {
        println("==== Company and Project Management ====")
        println("1. Display all companies and their projects")
        println("2. Add a new project to a company")
        println("3. Update a project of a company")
        println("4. Delete a project from a company")
        println("5. Exit")
        print("Select an option: ")

        when (scanner.nextInt()) {
            1 -> service.displayAllCompanies()
            2 -> addNewProject(scanner, service)
            3 -> updateProject(scanner, service)
            4 -> deleteProject(scanner, service)
            5 -> {
                println("Exiting...")
                return
            }
            else -> println("Invalid option. Please try again.")
        }
    }
}

fun addNewProject(scanner: Scanner, service: CompanyService) {
    print("Enter company ID: ")
    val companyId = scanner.nextInt()
    scanner.nextLine() // Consume newline
    print("Enter project ID: ")
    val projectId = scanner.nextInt()
    scanner.nextLine() // Consume newline
    print("Enter project name: ")
    val projectName = scanner.nextLine()
    print("Enter project description: ")
    val projectDescription = scanner.nextLine()

    service.addNewProject(companyId, Project(projectId, projectName, projectDescription))
}

fun updateProject(scanner: Scanner, service: CompanyService) {
    print("Enter company ID: ")
    val companyId = scanner.nextInt()
    scanner.nextLine() // Consume newline
    print("Enter project ID: ")
    val projectId = scanner.nextInt()
    scanner.nextLine() // Consume newline
    print("Enter updated project name: ")
    val updatedProjectName = scanner.nextLine()
    print("Enter updated project description: ")
    val updatedProjectDescription = scanner.nextLine()

    service.updateProject(companyId, Project(projectId, updatedProjectName, updatedProjectDescription))
}

fun deleteProject(scanner: Scanner, service: CompanyService) {
    print("Enter company ID: ")
    val companyId = scanner.nextInt()
    print("Enter project ID: ")
    val projectId = scanner.nextInt()

    service.deleteProject(companyId, projectId)
}
