package dev.datlag.tooling.country


data object Chile : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("CL")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("CHL")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(152)
}