

const connect = (funTab, fun) => { 
    
    Promise.all([Promise.all(funTab)]).then((el) => {
        
        console.log(el)
        const tab1 = []
        const tab = el[0]
        for (i of tab) {
            tab1.push(i)
        }
        console.log(tab1)
        const tab2 = tab1.map((el) => {return foo(el)}) 
        console.log(tab2)
        Promise.all(tab2).then((data) => console.log(data))
        const result = []
        tab1.forEach((el,index) => {
            result.push([el,tab2[index]])
        })
        console.log(result)
        
        
        
        
        
        
        
    })
    
    
    
    
    
};

const foo1 = async () => {return 1}
const foo2 = async () => {return 2}
const foo3 = async () => {return 3}
const funTab = [foo1(),foo2(),foo3()]
const foo = async (x) => {return x+1}
connect(funTab,foo)