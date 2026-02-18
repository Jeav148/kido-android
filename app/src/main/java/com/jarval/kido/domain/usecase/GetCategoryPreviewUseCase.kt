package com.jarval.kido.domain.usecase

import com.jarval.kido.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoryPreviewUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
){

    suspend operator fun invoke() = categoryRepository.getCategoriesPreview()

}