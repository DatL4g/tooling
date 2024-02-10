package dev.datlag.tooling.country


data object Niue : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("NU")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("NIU")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(570)
}