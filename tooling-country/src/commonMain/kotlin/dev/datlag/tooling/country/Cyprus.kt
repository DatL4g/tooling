package dev.datlag.tooling.country


data object Cyprus : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("CY")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("CYP")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(196)
}