package dev.datlag.tooling.country


data object Nepal : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("NP")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("NPL")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(524)
}