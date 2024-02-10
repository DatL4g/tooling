package dev.datlag.tooling.country


data object Bonaire : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("BQ")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("BES")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(535)
}