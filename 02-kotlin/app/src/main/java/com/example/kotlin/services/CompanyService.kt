package com.example.kotlin.services

import com.example.kotlin.entities.Project
import com.example.kotlin.repository.CompanyRepository

class CompanyService(private val repository: CompanyRepository) {

    fun displayAllCompanies() {
        repository.companies.forEach { company ->
            println("Company: ${company.name}")
            company.projects.forEach { project ->
                println("  Project: ${project.name} - ${project.description}")
            }
        }
    }

    fun addNewProject(companyId: Int, project: Project) {
        repository.createProject(companyId, project)
        println("Project added successfully.")
    }

    fun updateProject(companyId: Int, updatedProject: Project) {
        repository.updateProject(companyId, updatedProject)
        println("Project updated successfully.")
    }

    fun deleteProject(companyId: Int, projectId: Int) {
        repository.deleteProject(companyId, projectId)
        println("Project deleted successfully.")
    }
}
