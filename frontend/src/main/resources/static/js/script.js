let howToIsOpen = false;
const readMore = " read more >>";
const showLess = " << show less";
let longText = '';
let shortText = '';


$(document).ready(() => {
    let currentHowTo = $("div.how-to-container").attr('id');

    let url = '/static/js/json/data.json';
    const getJsonData = async () => {
        let response = await fetch(url)
            .then(r => r.json())
            .then((data) => {
                longText = data[currentHowTo] || "";
                shortText = longText.slice(0, 75);
                $("span.how-to-text").text(shortText);
            })
    };

    $("div.how-to-container").length !=0 && getJsonData(url).then(r => {});

    let onLoaded = sessionStorage.getItem('onload');
    if (onLoaded == null) {
        onLoaded = 0;
        sessionStorage.setItem('current', 'device');
    }
    onLoaded++;
    sessionStorage.setItem('onload', `${onLoaded}`)

    let currentLocationFromSessionStorage = sessionStorage.getItem('current');
    $(`a.nav-link[title=${currentLocationFromSessionStorage}]`).css({
        background: "linear-gradient(90deg, #194e5e 10%, transparent 10%)",
        zIndex: 2
    });

});


const currentLocation = window.location.pathname.split(/\//g)[1];
let selectedMenuitem = "";
let sessionStorage = window.sessionStorage;


//kiválasztani a keyt és paramban odaadni
const openHowTo = () => {
    if (howToIsOpen) {
        $("span.how-to-text").text(shortText);
        $("span.read-more").text(readMore);
        howToIsOpen = false;
    } else {
        $("span.how-to-text").text(longText);
        $("span.read-more").text(showLess);
        howToIsOpen = true;
    }
};

$("div.menu-item").on({
    mouseenter: function () {
        const link = $(this)[0].getBoundingClientRect();

        const xPos = link.width / 2 + link.left;
        const yPos = (link.top + link.height / 2) - 80;

        const translate = "translate(" + xPos + "px," + yPos + "px)";
        const scale = `scale(${link.width + 60},${link.height + 20})`;

        $("div.underline").css({
            transform: translate + " " + scale,
            transformOrigin: "center"
        });

    },
    click: function () {
        // todo mi legyen az annimáció "lenyomáskor"
        selectedMenuitem = $(this).children('a.nav-link').attr('title');
        sessionStorage.setItem("current", selectedMenuitem);
    }
});

$("div.menu-item-container").on({
    mouseleave: function () {
        $("div.underline").css({
            transform: "scale(0,0) translate(0px,0px)"
        })
    }
});

$("g#g1389").hover(
    function () {
        $(this).css({
            transform: "translate(3px, 44px)",
            transition: "all 150ms ease-in-out"
        });
        $("g#g1642").css({
            transform: "rotate(11deg)",
            transformOrigin: "top",
            transition: "all 150ms ease-in-out"
        });
        $("path#path1200").animate();
        setTimeout(function () {
            $("g#g1389").css({
                transform: "translate(3px, 48px) ",
                transition: "all 150ms ease-in-out"
            });
            $("g#g1642").css({
                transform: "rotate(0deg)",
                transformOrigin: "top",
                transition: "all 150ms ease-in-out"
            });
        }, 350);
    }, function () {
    }
);

let toggleSupportDiv = false;

$("div.support-container").click(function () {
    if (toggleSupportDiv) {
        $(this).removeClass("row");
        $("div.support-container div").removeClass("col-sm-4");
        $("div.support-text.col-sm-8").hide();
        toggleSupportDiv = false;
    } else {
        $(this).addClass("row");
        $("div.support-container div").addClass("col-sm-4");
        $("div.support-text.col-sm-8").toggle();
        toggleSupportDiv = true;
    }
});