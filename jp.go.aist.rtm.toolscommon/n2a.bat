for %%A in (%1) do (
  native2ascii.exe %%A %%A.tmp
  del %%A
  move %%A.tmp %%A
)
