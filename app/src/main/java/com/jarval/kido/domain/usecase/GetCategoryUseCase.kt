package com.jarval.kido.domain.usecase

import com.jarval.kido.domain.model.feature.dashboard.CategoryItem
import com.jarval.kido.domain.repository.CategoryRepository
import jakarta.inject.Inject

class GetCategoryUseCase @Inject constructor(
    val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(): List<CategoryItem> {
        return categoryRepository.getCategories()
    }

}