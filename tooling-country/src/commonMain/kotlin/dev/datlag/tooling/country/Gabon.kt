package dev.datlag.tooling.country


data object Gabon : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("GA")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("GAB")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(266)
}