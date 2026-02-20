package com.jarval.kido.presentation.feature.dashboard

import app.cash.turbine.test
import com.jarval.kido.domain.model.feature.dashboard.CategoryItem
import com.jarval.kido.domain.model.feature.dashboard.ProductItem
import com.jarval.kido.domain.usecase.GetCategoryPreviewUseCase
import com.jarval.kido.domain.usecase.GetPopularProductUseCase
import com.jarval.kido.presentation.navigation.NavigationDispatcher
import com.jarval.kido.presentation.navigation.Screen
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    //Setup dispatcher
    private val testDispatcher = StandardTestDispatcher()

    //Setup dependencies
    private val getCategoriesUseCase = mockk<GetCategoryPreviewUseCase>()
    private val getPopularProductUseCase = mockk<GetPopularProductUseCase>()
    private val navigationDispatcher = mockk<NavigationDispatcher>()

    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `initialization triggers data loading and updates with Success`() = runTest {
        //GIVEN
        coEvery { getCategoriesUseCase() } returns mockCategories
        coEvery { getPopularProductUseCase(any()) } returns mockProducts

        //WHEN
        viewModel = DashboardViewModel(
            getCategoriesUseCase =  getCategoriesUseCase,
            getPopularProductUseCase = getPopularProductUseCase,
            navigationDispatcher = navigationDispatcher
        )
        //THEN
        viewModel.state.test {
            awaitItem() // consume initial state
            testDispatcher.scheduler.advanceUntilIdle()
            val finalState = awaitItem().categoryState
            val finalProductState = awaitItem().productState

            assertTrue(finalState is CategoryState.Success)
            assertTrue(finalProductState is ProductState.Success)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `NavigateToCategory should call navigationDispatcher with correct screen`() = runTest {
        //GIVEN
        every { navigationDispatcher.navigateTo(any()) } just runs
        viewModel = DashboardViewModel(
            getCategoriesUseCase =  getCategoriesUseCase,
            getPopularProductUseCase = getPopularProductUseCase,
            navigationDispatcher = navigationDispatcher
        )
        val categoryName = "Test Name"

        //WHEN

        viewModel.navigateToCategory(categoryName)

        //THEN
        verify {
            navigationDispatcher.navigateTo(Screen.Category(categoryName))
        }
    }

    private val mockCategories = listOf<CategoryItem>(
        CategoryItem(1, "Baby Diapers", "12 products"),
        CategoryItem(1, "Makeup Removers", "8 products"),
        CategoryItem(1, "Period Pads", "21 products"),
        CategoryItem(1, "Lactation Pads", "6 products"),
        CategoryItem(1, "Handkerchiefs", "6 products"),
    )
    private val mockProducts = listOf<ProductItem>(
        ProductItem(
            id = 1,
            title = "Diapers",
            amount = "10",
            price = "$12.50",
            favorite = true
        ),
        ProductItem(
            id = 2,
            title = "Diapers",
            amount = "9",
            price = "$12.50",
            favorite = false
        ),
        ProductItem(
            id = 3,
            title = "Diapers",
            amount = "8",
            price = "$12.50",
            favorite = true
        ),
        ProductItem(
            id = 4,
            title = "Diapers",
            amount = "7",
            price = "$12.50",
            favorite = false
        ),
        ProductItem(
            id = 5,
            title = "Diapers",
            amount = "6",
            price = "$12.50",
            favorite = true
        ),
        ProductItem(
            id = 6,
            title = "Diapers",
            amount = "5",
            price = "$12.50",
            favorite = false
        )
    )


}