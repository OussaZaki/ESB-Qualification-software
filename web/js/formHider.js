$(document).ready(function () {
    $('#number').on('change', function () {
        var selection = $(this).val();
        switch (selection) {
            case "1":
                $("#peer1").show();
                $("#peer2").hide();
                $("#peer3").hide();
                $("#peer4").hide();
                $("#peer5").hide();
                break;
            case "2":
                $("#peer1").show();
                $("#peer2").show();
                $("#peer3").hide();
                $("#peer4").hide();
                $("#peer5").hide();
                break;
            case "3":
                $("#peer1").show();
                $("#peer2").show();
                $("#peer3").show();
                $("#peer4").hide();
                $("#peer5").hide();
                break;
            case "4":
                $("#peer1").show();
                $("#peer2").show();
                $("#peer3").show();
                $("#peer4").show();
                $("#peer5").hide();
                break;
            case "5":
                $("#peer1").show();
                $("#peer2").show();
                $("#peer3").show();
                $("#peer4").show();
                $("#peer5").show()
                break;
            default:
                $("#peer1").hide();
                $("#peer2").hide();
                $("#peer3").hide();
                $("#peer4").hide();
                $("#peer5").hide();

        }
    });
});
