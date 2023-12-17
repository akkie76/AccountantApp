package jp.co.accountant.domain.expense.data

/**
 * 入力した金額と扱うデータクラス
 *
 * @param eightPercent 8%金額
 * @param tenPercent 10%金額
 */
data class AmountMoney(
    val eightPercent: String,
    val tenPercent: String
) {
    val total: String
        get() = (eightPercent.toInt() + tenPercent.toInt()).toString()
}
