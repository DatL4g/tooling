package dev.datlag.tooling.country


data object HolySee : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("VA")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("VAT")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(336)
}