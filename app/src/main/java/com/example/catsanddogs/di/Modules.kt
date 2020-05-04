package com.example.catsanddogs.di

import com.example.catsanddogs.ui.main.PageViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PageViewModel()
    }
}