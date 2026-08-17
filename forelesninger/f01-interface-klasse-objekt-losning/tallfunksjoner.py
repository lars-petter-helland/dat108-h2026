#
# Dette eksempelet er tenkt som forløper til samme Java-eksempel
# i forelesning f01 i DAT108.
#
# Er litt lax med "typehint", selv om jeg alltid ville brukt slike
# i virkeligheten.
#
from collections.abc import Callable

# Funksjon - En funksjon som dobler et tall
def dobbel(tall: int) -> int:
    return 2 * tall # PEP 8 anbefaler å ha return på ny/egen linje.

print("dobbel(7) =", dobbel(7))

# Liste - Python har ingen innebygget tabell-type, men bruker list i stedet
listen: list[int] = [1, 2, 3, 4]

# En funksjon som lager en ny liste med dobling av alle verdiene i listen
def dobbel_liste(liste):
    ny_liste = []
    for tall in liste:
        ny_liste.append(dobbel(tall))
    return ny_liste

print("dobbel_liste(..) =", dobbel_liste(listen))

# En funksjon som lager en ny liste med f av alle verdiene i listen
# def oppdater_verdier(liste: list[int], f: Callable[[int], int]) -> list[int]:
# def oppdater_verdier(liste: list[int], f: Callable) -> list[int]:
def omformet_liste(liste, f: Callable):
    ny_liste = []
    for tall in liste:
        ny_liste.append(f(tall))
    return ny_liste

print("omformet_liste(.. dobbel) =", omformet_liste(listen, dobbel))

# En funksjon som kvadrerer et tall
def kvadrat(tall: int) -> int: 
    return tall * tall

print("omformet_liste(.. kvadrat) =", omformet_liste(listen, kvadrat))

# ... litt videre  ...
print("omformet_liste(.. lambda 3*x) =", omformet_liste(listen, lambda x: 3*x))
print("map(.. lambda 4*x) =", list(map(lambda x: 4*x, listen)))
print("[5*x for ..]] =", [5*x for x in listen])

