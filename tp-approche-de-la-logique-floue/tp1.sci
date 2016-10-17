function ex12()
    xlabel("Température (°C)");
    x=1:40
    y1 = basse();
    subplot(2,1,1);
    plot2d(x,y1,style=color("blue"));


    y2 = moyenne();
    plot2d(x,y2,style=color("green"));

    y3 = haute();

    plot2d(x,y3,style=color("red"));
//    legends(['Basse';'Moyenne';'Élevee'],[2,3,5],3);  
    q3 = y1;
    for i=1:40
        if q3(i) < y2(i)
            q3(i) = y2(i)
        end
    end
    
    subplot(2,1,2);
    plot2d(x,q3,style=color("black"));
    
    printf("Question 2 : valeur sous-ensemble à 16°C\n");
    printf("Basse : %f\n", y1(16));
    printf("Moyenne : %f\n", y2(16));
    printf("Elevée : %f\n", y3(16));

endfunction

function ex3()
    xlabel("Puissance de chauffe");
    x = 1:15;
    y1 = basse();
    chauffe = wattChaleur();
    subplot(2,1,1);
    plot2d(x,chauffe,style=color("blue"));

    appartenanceY12 = y1(12);
    printf("limite : %f\n", appartenanceY12);
    limite = remplissage(appartenanceY12, 15);
    mamdani = min(chauffe, limite);
    subplot(2,1,2);
    plot2d(x,mamdani,style=color("blue"));

endfunction

function res = remplissage (valeur, tabSize)
    res=1:tabSize
    for i=1:tabSize
        res(i) = valeur;
    end
endfunction


function res = basse ()
    res=1:40
    for i=1:10
        res(i) = 1;
    end
    for i=11:20
        res(i) = 1 - (i-10)/10;
    end
    for i=21:40
        res(i) = 0;
    end
endfunction

function res = moyenne ()
    res=1:40
    for i=1:10
        res(i) = 0;
    end
    for i=11:20
        res(i) =(i-10)/10;
    end
    for i=21:30
        res(i) = 1 - (i-20)/10;
    end
    for i=31:40
        res(i) = 0;
    end
endfunction

function res = haute ()
    res=1:40
    for i=1:20
        res(i) = 0;
    end
    for i=21:30
        res(i) =(i-20)/10;
    end
    for i=31:40
        res(i) = 1;
    end
endfunction

function res = wattChaleur ()
    res=1:15
    for i=1:8
        res(i) = 0;
    end
    for i=9:10
        res(i) =((i-8)/2);
    end
    for i=11:15
        res(i) = 1;
    end
endfunction

function res = min(g1, g2)
    res = g1;
    for i=1:length(res)
        if res(i) > g2(i)
            res(i) = g2(i)
        end
    end
endfunction


function res = max(g1, g2)
    res = g1;
    for i=1:length(res)
        if res(i) < g2(i)
            res(i) = g2(i)
        end
    end
endfunction
