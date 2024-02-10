package dev.datlag.tooling.country


data object Aruba : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("AW")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("ABW")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(533)
}