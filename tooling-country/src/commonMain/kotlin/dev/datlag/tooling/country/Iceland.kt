package dev.datlag.tooling.country


data object Iceland : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("IS")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("ISL")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(352)
}