$(document).ready(() => {

    let onLoaded = window.sessionStorage.getItem('onload');
    console.debug(onLoaded)
    if (onLoaded==null) {
        onLoaded = 0;
        window.sessionStorage.setItem('current', 'device');
    }
    onLoaded++;
    window.sessionStorage.setItem('onload', `${onLoaded}`)

    let currentLocationFromSessionStorage = window.sessionStorage.getItem('current');
    $(`a.nav-link[title=${currentLocationFromSessionStorage}]`).css({background: "linear-gradient(90deg, #194e5e 10%, transparent 10%)", zIndex:2});
});


const currentLocation = window.location.pathname.split(/\//g)[1];
let selectedMenuitem = "";
let myStorage = window.sessionStorage;


let howToIsOpen = false;

//ez jöhet be-ről is
const howTo = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has\n" +
    " been the industry's standard dummy text ever since the 1500s, when an unknown printer took a\n" +
    " galley of type and scrambled it to make a type specimen book. It has survived not only five\n" +
    " centuries.";

const readMore = " read more >>";
const showLess = " << show less";

let short = howTo;
short = short.slice(0, 75);
$("span.how-to-text").text(short);
$("span.read-more").text(readMore);


const openHowTo = () => {
    const howToText = howTo;
    if (howToIsOpen) {
        $("span.how-to-text").text(short);
        $("span.read-more").text(readMore);
        howToIsOpen = false;
    } else {
        $("span.how-to-text").text(howToText);
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
        myStorage.setItem("current", selectedMenuitem);
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
        $("path#path1200").animate()
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