function myFunctionReport(checkboxId, selectId) {
    if ($(checkboxId).is(':checked')) {
        $(selectId).prop('disabled', true);
    } else {
        $(selectId).prop('disabled', false);
    }
}
