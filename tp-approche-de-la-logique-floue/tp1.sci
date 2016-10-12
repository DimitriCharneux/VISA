function graph()
    xlabel("Température (°C)");
    x=1:40
    y1=1:40
    for i=1:10
        y1(i) = 1;
    end
    for i=11:20
        y1(i) = 1 - (i-10)/10;
    end
    for i=21:40
        y1(i) = 0;
    end
    subplot(2,1,1);
    plot2d(x,y1,style=color("blue"));


    y2=1:40
    for i=1:10
        y2(i) = 0;
    end
    for i=11:20
        y2(i) =(i-10)/10;
    end
    for i=21:30
        y2(i) = 1 - (i-20)/10;
    end
    for i=31:40
        y2(i) = 0;
    end
    plot2d(x,y2,style=color("green"));

    
    y3=1:40
    for i=1:20
        y3(i) = 0;
    end
    for i=21:30
        y3(i) =(i-20)/10;
    end
    for i=31:40
        y3(i) = 1;
    end

    plot2d(x,y3,style=color("red"));
    legends(['Basse';'Moyenne';'Élevee'],[2,3,5],3);
    
    q3 = y1;
    for i=1:40
        if q3(i) < y2(i)
            q3(i) = y2(i)
        end
    end
    
    subplot(2,1,2);
    plot2d(x,q3,style=color("black"));
    xlabel("Température (°C)");
    legends('Basse ou Moyenne',1,3);
    
    printf("Question 2 : valeur sous-ensemble à 16°C\n");
    printf("Basse : %f\n", y1(16));
    printf("Moyenne : %f\n", y2(16));
    printf("Elevée : %f\n", y3(16));

endfunction
