package ru.alexsergeev.presentation.utils.mappers

internal interface Mapper<in I, out O> {
    fun map(input: I): O
}