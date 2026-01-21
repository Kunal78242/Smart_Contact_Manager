console.log("this is script tag");

const toggleSidebar = () => {
    const sidebar = $(".sidebar");
    const content = $(".content");
    
    if (sidebar.is(":visible")) {
        // true - have to close
        sidebar.css("display", "none");
        content.css("margin-left", "0%");
    } else {
        // false - have to show
        sidebar.css("display", "block");
        content.css("margin-left", "20%");
    }
};
