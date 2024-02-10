package dev.datlag.tooling.country


data object Armenia : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("AM")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("ARM")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(51)
}