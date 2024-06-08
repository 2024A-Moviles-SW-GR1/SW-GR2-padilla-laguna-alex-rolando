package com.example.kotlin.repository

import com.example.kotlin.entities.Company
import com.example.kotlin.entities.Project
import java.io.File

class CompanyRepository() {

    val companies: MutableList<Company> = mutableListOf()

    init {
        loadFromFile("./data.txt")
    }

    fun createProject(companyId: Int, project: Project) {
        val company = companies.find { it.id == companyId }
        company?.projects?.add(project)
    }

    fun readProjects(companyId: Int): List<Project>? {
        val company = companies.find { it.id == companyId }
        return company?.projects
    }

    fun updateProject(companyId: Int, updatedProject: Project) {
        val company = companies.find { it.id == companyId }
        company?.projects?.let { projects ->
            val projectIndex = projects.indexOfFirst { it.id == updatedProject.id }
            if (projectIndex != -1) {
                projects[projectIndex] = updatedProject
            }
        }
    }

    fun deleteProject(companyId: Int, projectId: Int) {
        val company = companies.find { it.id == companyId }
        company?.projects?.removeIf { it.id == projectId }
    }

    private fun loadFromFile(filePath: String) {
        val file = File(filePath)
        var currentCompany: Company? = null

        file.forEachLine { line ->
            val parts = line.split(",")
            if (parts.size == 2) { // It's a company line
                if (currentCompany != null) {
                    companies.add(currentCompany!!)
                }
                currentCompany = Company(parts[0].toInt(), parts[1], mutableListOf())
            } else if (parts.size == 3 && currentCompany != null) { // It's a project line
                val project = Project(parts[0].toInt(), parts[1], parts[2])
                currentCompany!!.projects.add(project)
            }
        }

        // Add the last company if exists
        if (currentCompany != null) {
            companies.add(currentCompany!!)
        }
    }
}
