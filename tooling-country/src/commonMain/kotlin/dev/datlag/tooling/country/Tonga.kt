package dev.datlag.tooling.country


data object Tonga : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("TO")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("TON")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(776)
}