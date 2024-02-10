package dev.datlag.tooling.country


data object Haiti : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("HT")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("HTI")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(332)
}