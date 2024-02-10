package dev.datlag.tooling.country


data object Congo : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("CG")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("COG")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(178)
}