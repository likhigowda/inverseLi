import pandas as pd
import numpy as np

def on_size(m):
    if m == 2:
        return ['',''],['','      ']

    if m == 3:
        return ['','',''],['','      ','      ']

    if m == 4:
        return ['','','',''],['','      ','      ','      ']


def main(n):
    n = list(n)
    size = int(len(n) ** 0.5)

    my_array = np.array(n)
    my_array = np.reshape(my_array,(size,size))

    if(round(np.linalg.det(my_array),1) == 0.0):
        return "it's a singular matrix"

    else:
        ans = np.linalg.inv(my_array)
        rows,col = on_size(size)
        df = pd.DataFrame(ans,index=rows,columns=col).round(3).to_string()
        return "inverse of the given matrix is\n"+df



