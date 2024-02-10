package dev.datlag.tooling.country


data object Guinea : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("GN")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("GIN")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(324)
}