package dev.datlag.tooling.country


data object Iraq : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("IQ")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("IRQ")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(368)
}