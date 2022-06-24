package com.example.androidassesmenttest.domain.usecases.usecases

interface DtoIntoEntityMapper<Dto, Entity> {
    operator fun invoke(dto: Dto): Entity
}
