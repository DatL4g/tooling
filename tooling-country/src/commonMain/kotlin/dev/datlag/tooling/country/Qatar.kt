package dev.datlag.tooling.country


data object Qatar : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("QA")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("QAT")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(634)
}