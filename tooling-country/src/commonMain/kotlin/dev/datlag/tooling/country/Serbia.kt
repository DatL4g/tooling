package dev.datlag.tooling.country


data object Serbia : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("RS")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("SRB")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(688)
}