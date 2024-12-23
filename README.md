# SubstanceCraft
1.21.1 Fabric mod with chemistry features and drugs. 
Requires fabric API

Compiling from source: Requires java 21 and gradle 8.10

Disclaimer: for funsies only, I do not promote the selling, distribution, or consumption of drugs outside of this mod.

# Features
## Drugs
### Marijuana
Adds Marijuana plant along with marijuana, trim, and seeds. 
Marijuana can be found occasionally in jungles and seeds are often found in jungle temple chest loot.
Marijuana can be pressed into hash using a hash press. 
Dab rigs can be made from several glass blocks and an empty glass bottle. 
By clicking water, the dab rig will have a little water in the bottom, making it usable.
Using the dab rig while holding hash in the offhand will consume a hash and rip a dab.

- future plans: adding bongs and joints.

### Ketamine
For now, only crafted in the refinery using oil, currently only turns player invisible, 
but I hope to add more complex effects later on.

- future plans: ego death effect.

### Diphenhydramine
As a temporary recipe, crafted in the refinery from oil. Gives player a nice collection of
harmful effects. Would not recommend for human consumption
- future plans: hatman randomly is watching you (with hallucination effect). 
Entity is implemented, by behavior needs to be added.

### LSD
Not yet implemented, ergot is added, which will be used in the crafting of LSD.

- future drugs: alcohol, dmt

# Chemistry
Several chemistry block entities have been added, all of which can be crafted in the crafting table 
with iron, copper blocks, and other resources. Each block has a screen that contains a menu of 
all possible recipes along with input, output, and byproduct item slots. 
Times for crafting vary by recipe, a progress arrow shows remaining time.

### Refining
Used to craft materials related to oil and natural gas. 
Oil can be extracted from oil shale found in deep oceans. Natural gas is a byproduct of reducing oil shale to oil

### Electrolysis
Chemical process which electricity is used to create a chemical reaction.
Brine (crafted by mixing salt and water) can undergo electrolysis to convert into sodium hydroxide.
Salt is found in ocean biomes and dripstone caves.

### Oxidation
Chemical process inducing loss of electrons to a substance by addition of oxygen.

### Catalytic Reforming
Process similar to refining, used to convert petroleum naphtha into other compounds

### Air extraction
Process used to obtain compounds or elements found in the air, currently oxygen and nitrogen.

### Fermentation
The process of using bacteria such as yeast to create fermented compounds, such as ethanol
- future plans: add vinegar, alcohol, wine

### Combining compounds and elements
There are two block entities that perform this function, the mixer and the heated mixer. 
When combing elements requires heat to create a reaction, the heated mixer is used, otherwise the mixer is used.

# Crops
### Corn
Two block high crop that is used to create ethanol through fermentation

- future crop plans:
add wheat like plants, barley and hops, which will be used in fermentation to create alcoholic beverages
### Fungi
Ergot and yeast have a .15% chance of dropping from wheat, carrot, potatoes, and beetroot.
Can be duplicated in the fermentation tank by feeding the fungi sugar.
Note: fermentation is a slow process
