var inputQuantity = [];
$(function () {
    $(".numberChecking").each(function (i) {
        inputQuantity[i] = this.defaultValue;
        $(this).data("idx", i); // save this field's index to access later
    });
    $(".numberChecking").on("input", function (e) {
        var $field = $(this),
            val = this.value,
            $thisIndex = parseInt($field.data("idx"), 10); // retrieve the index
        //        window.console && console.log($field.is(":invalid"));
        //  $field.is(":invalid") is for Safari, it must be the last to not error in IE8
        if (this.validity && this.validity.badInput || isNaN(val) || $field.is(":invalid")) {
            this.value = inputQuantity[$thisIndex];
            return false;
        }
        if (val.length > Number($field.attr("maxlength"))) {
            val = val.slice(0, 5);
            $field.val(val);
        }
        inputQuantity[$thisIndex] = val;
    });
});

function setupDate(id) {
    $(id).datepicker({
        autoclose: true,
        format: "yyyy-mm-dd"
    });
}

function setDateTime(id) {
    $(id).datetimepicker({
        format: "YYYY-MM-DD HH:mm:ss",
        allowInputToggle: true,
        showTodayButton: true,
        showClose: true,
    });
}

function setupTime(id) {
    $(id).timepicker({
        showInputs: false
    })
}

function decodeData(html) {
    var txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}

function calculateAgeByDOB(dateString) {
    var now = new Date();
    var today = new Date(now.getYear(), now.getMonth(), now.getDate());

    var yearNow = now.getYear();
    var monthNow = now.getMonth();
    var dateNow = now.getDate();

    var dob = new Date(dateString);

    var yearDob = dob.getYear();
    var monthDob = dob.getMonth();
    var dateDob = dob.getDate();
    var age = {};
    var ageString = "";
    var yearString = "";
    var monthString = "";
    var dayString = "";


    yearAge = yearNow - yearDob;

    if (monthNow >= monthDob)
        var monthAge = monthNow - monthDob;
    else {
        yearAge--;
        var monthAge = 12 + monthNow - monthDob;
    }

    if (dateNow >= dateDob)
        var dateAge = dateNow - dateDob;
    else {
        monthAge--;
        var dateAge = 31 + dateNow - dateDob;

        if (monthAge < 0) {
            monthAge = 11;
            yearAge--;
        }
    }

    $("#selYear").val(yearAge);
    $("#selMonth").val(monthAge);
    $("#selDay").val(dateAge);
}

function calculateDOBByAge(year, month, day) {
    var today = new Date();
    var year = today.getFullYear() - year;
    var result = year + "-" + pad(today.getMonth() + 1, 2) + "-" + pad(today.getDate(), 2);
    result = moment(result, "YYYY-MM-DD").subtract(month, 'M').format('YYYY-MM-DD');
    result = moment(result, "YYYY-MM-DD").subtract(day, 'days').format('YYYY-MM-DD');

    return result;
}

function pad(num, count) {
    return ("0" + num).slice(-(count));
}

$.fn.extend({
    clearFiles: function () {
        $(this).each(function () {
            var isIE = (window.navigator.userAgent.indexOf("MSIE ") > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./));
            if ($(this).prop("type") == 'file') {
                if (isIE == true) {
                    $(this).replaceWith($(this).val('').clone(true));
                } else {
                    $(this).val("");
                }
            }
        });
        return this;
    }
});