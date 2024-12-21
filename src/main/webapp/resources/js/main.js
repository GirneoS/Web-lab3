$(document).ready(function(){
    const Xs = $('#main-form > a');
    Xs.each(function () {
        $(this).click(() => {
            Xs.removeClass("selected");
            $(this).addClass("selected");
        });
    });

    makeDiagram();
    const set_r_btn = document.getElementById("main-form\:set-r-btn");
    set_r_btn.addEventListener("click",()=>fillDiagram());

    // const table_btns = $('#result-table_paginator_bottom > a');
    // table_btns.each(function () {
    //     $(this).click(()=>{
    //         fillDiagram();
    //     })
    // })

    const canvas = document.getElementById("canvas");
    canvas.onmousedown = mouseHandler;
    function mouseHandler(evt) {
        let r = document.getElementById("main-form\:set-r").value;
        if (r>=2 && r<=5) {
            let elementCanvas = document.getElementById("canvas");
            if (evt.target === elementCanvas) {
                let rect = elementCanvas.getBoundingClientRect();
                let zeroedX = rect.left + 200;
                let zeroedY = rect.top + 200;
                let x = Math.round(((evt.clientX - zeroedX) / 160) * 5 * 1000) / 1000;
                let y = Math.round(((-(evt.clientY - zeroedY)) / 160) * 5 * 1000) / 1000;
                if(r>=2 && r<=5) {
                    console.log(`x: ${x} y: ${y} r: ${r}`)
                    savePointCommand([{name: "x", value: x.toString()}, {name: "y", value: y.toString()}, {name:"r", value: r.toString()}]);
                    setTimeout(fillDiagram,20000);
                }
            }
        }else{
            toastr.error("r должен быть в диапазоне [2,5]");
        }
    }
});

function makeDiagram(){
    let canvas = $("#canvas")[0].getContext("2d");
    canvas.beginPath();
    canvas.clearRect(0,0,400,400);
    //горизонтальная линия
    canvas.moveTo(0,200);
    canvas.lineTo(400,200);
    //вертикальная линия
    canvas.moveTo(200,0);
    canvas.lineTo(200,400);
    //верхняя стрелка
    canvas.moveTo(195,10);
    canvas.lineTo(200,0);
    canvas.lineTo(205,10);
    canvas.lineTo(195,10);
    //правая стрелка
    canvas.moveTo(390,195);
    canvas.lineTo(400,200);
    canvas.lineTo(390,205);
    canvas.lineTo(390,195);



    canvas.font="16px Fantasy";

    canvas.fillText("y",207,10);
    canvas.fillText("x",393,212);

    canvas.closePath();
    canvas.fillStyle = "black";
    canvas.stroke();
    canvas.fill();
}
function fillDiagram(){
    let r = document.getElementById("main-form\:set-r").value;
    if(r>=2 && r<=5){
        r = r/5
        let canvas = document.getElementById("canvas").getContext("2d");
        canvas.beginPath();
        canvas.clearRect(0,0,400,400);
        makeDiagram();
        drawDots();
        canvas.closePath();

        canvas.beginPath();
        canvas.fillStyle = "black";
        //отметки на горизонтальной линии
        canvas.moveTo(200+160*r,197);
        canvas.lineTo(200+160*r,203);
        canvas.moveTo(200+80*r,197);
        canvas.lineTo(200+80*r,203);
        canvas.moveTo(200-160*r,197);
        canvas.lineTo(200-160*r,203);
        canvas.moveTo(200-80*r,197);
        canvas.lineTo(200-80*r,203);

        //отметки на вертикальной линии
        canvas.moveTo(197,200-160*r);
        canvas.lineTo(203,200-160*r);
        canvas.moveTo(197,200-80*r);
        canvas.lineTo(203,200-80*r);
        canvas.moveTo(197,200+80*r);
        canvas.lineTo(203,200+80*r);
        canvas.moveTo(197,200+160*r);
        canvas.lineTo(203,200+160*r);

        canvas.font="16px Fantasy";

        canvas.fillText("R",200+160*r-3,193);
        canvas.strokeText("R/2",200+80*r-3,193);
        canvas.strokeText("-R/2",200-80*r-3,193);
        canvas.strokeText("-R",200-160*r-3,193)

        canvas.strokeText("R",207,200-160*r-3);
        canvas.strokeText("R/2",207,200-80*r-3);
        canvas.strokeText("-R/2",207,200+80*r-3);
        canvas.strokeText("-R",207,200+160*r-3);
        canvas.stroke();

        canvas.rect(201,201,r*160,r*80);
        //треугольник в 4-й четверти
        canvas.moveTo(201,199-160*r); //самая верхняя точка
        canvas.lineTo(360-160*(1-r),201); //самая правая точка
        canvas.lineTo(201,201); //
        canvas.lineTo(201,199-160*r); //

        canvas.moveTo(199,199);
        canvas.arc(199,199,160*r,Math.PI,3*Math.PI/2,false);
        canvas.lineTo(199,199);

        canvas.closePath();
        canvas.fillStyle = "rgba(98, 24, 40, 0.4)";

        canvas.fill();
    }
}
function drawDots(){
    let t_body = $("#result-table_data > tr");

    if(t_body.length>0) {
        let canvas = $("#canvas")[0].getContext("2d");

        for (let i = 0; i < t_body.length; i++) {
            let r = parseFloat(t_body[i].children[2].innerHTML);
            let xInTable = parseFloat(t_body[i].children[0].innerHTML);
            let yInTable = parseFloat(t_body[i].children[1].innerHTML);

            let xCoordinate = 200 + (xInTable/5)*160;
            let yCoordinate = 200 + ((-1)*(yInTable/5)*160);

            canvas.beginPath();
            canvas.arc(xCoordinate, yCoordinate, 5, 0, Math.PI * 2);
            canvas.closePath();
            if(t_body[i].children[3].textContent.trim() === "попал") {
                canvas.fillStyle = "green";
                canvas.fill();
            }else {
                canvas.fillStyle = "red";
                canvas.fill();
            }
        }
    }
}
