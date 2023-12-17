package jp.co.accountant.domain.expense

enum class SegmentType(val value: Int) {
    NONE(0),
    NAME(1),
    CODE(2);

    companion object {
        /**
         * SegmentTypeを生成する
         *
         * @param value value
         * @return SegmentType
         */
        fun from(value: Int): SegmentType {
            return entries.first { it.value == value }
        }
    }
}
