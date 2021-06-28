package com.assessment.recipemanager.configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.assessment.recipe-manager.repository")
public class RepositoryConfig {
}
