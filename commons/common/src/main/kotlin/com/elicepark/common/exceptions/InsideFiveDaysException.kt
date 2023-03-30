package com.elicepark.common.exceptions

import com.elicepark.common.result.ErrorResults

class InsideFiveDaysException(val fieldError: ErrorResults.FieldError
) : RuntimeException() {

}
