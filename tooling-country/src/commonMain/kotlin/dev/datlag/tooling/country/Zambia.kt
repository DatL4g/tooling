package dev.datlag.tooling.country


data object Zambia : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("ZM")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("ZMB")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(894)
}