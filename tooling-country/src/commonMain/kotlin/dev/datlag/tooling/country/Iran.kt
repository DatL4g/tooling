package dev.datlag.tooling.country


data object Iran : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("IR")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("IRN")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(364)
}