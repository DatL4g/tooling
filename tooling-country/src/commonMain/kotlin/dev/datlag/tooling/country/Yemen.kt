package dev.datlag.tooling.country


data object Yemen : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(887)
}